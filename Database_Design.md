## There should be two tables in the database

1. User Table  
   User_id  
   Username  
   Password  
   Email
2. Todo Table
   TaskID  
   UserID REFERENCES Users(UserID)  
   Title  
   Description  
   DueDate  
   Status
