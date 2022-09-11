# Drones management application (demo project)

### Reference Documentation
Minimal Java Version required: 17

Application provides the following REST API:

- Drone registration:
  POST /api/v1/drones/register  
  parameters:
  - Drone (json)
- Medication loading:
  POST /api/v1/drones/{droneSerialNumber}/load  
  parameters:
  - droneSerialNumber : String
  - Medication (json)
- List drone medication items:
  GET /api/v1/drones/{droneSerialNumber}/items  
  parameters:
  - droneSerialNumber : String
- List available drones:  
  GET /api/v1/drones/available
- Show all drones battery level:
  GET /api/v1/drones/{droneSerialNumber}/battery  
  parameters:
  - droneSerialNumber : String

Drone JSON example:
```
{
    "serialNumber": "12345",
    "model": "LIGHTWEIGHT",
    "batteryCapacity": 55,
    "state": "IDLE"
}
```

Medication JSON example:
```
{
    "name": "ibuprofen",
    "weight": 40,
    "code": "IBP_0101",
    "picture": "https://drsilvasultrawellness.com/wp-content/uploads/2019/04/00749603_dghl_ibuprofen_brown_500ct.jpg"
}
```
