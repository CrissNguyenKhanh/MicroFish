param(
	[string]$BaseUrl = "http://localhost:8080"
)

$ErrorActionPreference = "Stop"

function Invoke-JsonGet {
	param([string]$Path)
	Invoke-RestMethod -Method Get -Uri ($BaseUrl + $Path)
}

function Invoke-JsonPost {
	param(
		[string]$Path,
		[object]$Body
	)

	Invoke-RestMethod -Method Post -Uri ($BaseUrl + $Path) -ContentType "application/json" -Body ($Body | ConvertTo-Json)
}

function Invoke-JsonPut {
	param(
		[string]$Path,
		[object]$Body
	)

	Invoke-RestMethod -Method Put -Uri ($BaseUrl + $Path) -ContentType "application/json" -Body ($Body | ConvertTo-Json)
}

Write-Host "Check health..."
$health = Invoke-JsonGet "/actuator/health"
if ($health.status -ne "UP") {
	throw "Health status not UP"
}

$email = "duong.$([DateTimeOffset]::UtcNow.ToUnixTimeMilliseconds())@example.com"

Write-Host "Create user..."
$created = Invoke-JsonPost "/users" @{
	name = "Duong"
	email = $email
}

if (-not $created.id) {
	throw "Create user failed"
}

Write-Host "Get user..."
$found = Invoke-JsonGet ("/users/" + $created.id)
if ($found.email -ne $email) {
	throw "Get user mismatch"
}

Write-Host "List users..."
$users = Invoke-JsonGet "/users"
if ($users.Count -lt 1) {
	throw "List users empty"
}

Write-Host "Update user..."
$updated = Invoke-JsonPut ("/users/" + $created.id) @{
	name = "Duong Updated"
	email = "updated.$email"
}

if ($updated.name -ne "Duong Updated") {
	throw "Update user failed"
}

Write-Host "Delete user..."
Invoke-RestMethod -Method Delete -Uri ($BaseUrl + "/users/" + $created.id) | Out-Null

Write-Host "Verify delete..."
try {
	Invoke-JsonGet ("/users/" + $created.id) | Out-Null
	throw "Delete user did not remove record"
}
catch {
	if ($_.Exception.Response.StatusCode.value__ -ne 404) {
		throw
	}
}

Write-Host "Smoke test done."
