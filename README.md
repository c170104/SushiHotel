# SushiHotel

TODO Checklist:
    GuestManager


Completed Classes:
    Backend Serialized file implementation
        - FlatFileIO (Concrete Class)
        - IDataStore (Interface)
        - DataStoreFactory (Factory)
    Guest
        - Entity
        - Model
    Exception
        - DuplicateData
        - EmptyDB
        - InvalidEntity
    Tools
        - Property file Reader/Handler
    Resources
        - Config file
        - Database file: Guest

Features: (includes Exception handling)
    Register Guest (Done)

HotelMgr:
    Guest: 
        Register, Edit, Search (Done)
    Room: 
        Create, Edit (Done)
    Business Logic:
        Call Room Svc, Check room Availability (Done)
        Check in, Check out (not done)
