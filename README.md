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
        Register, Edit, Search (Done)(All Tested and working)
    Room: 
        Create, Edit (Done)(All tested and working)
    Menu:
        Create, Edit, Remove, print (Done)(All tested and working)
    Business Logic:
        Call Room Svc, Check room Availability (Done)(testing and working)
        Check in (Done)(working), Check out (done)(working)
    Logging:
        Global logger (Done)(Tested)
