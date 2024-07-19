## Database Tables

### 1. User Table

The `User` table contains user-specific information. Below are the fields for the `User` table:

- **User_id** (int): The unique identifier for each user, auto-incremented.
- **Username** (varchar): The username used for login and display purposes.
- **Password** (varchar): The hashed password for user login.
- **Email** (varchar): The user's email address, must be unique.

### 2. Todo Table

The `Todo` table lists the tasks assigned to users. It has the following fields:

- **TaskID** (int): The unique identifier for each task, auto-incremented.
- **Completed** (boolean): Whether this task is compeleted or not.
- **Title** (varchar): The title of the task.
- **Description** (text): A detailed description of the task.
- **CreatedDate** (date): The time for user create this task.
- **ModifiedDate** (date): The last time user modify this task.
- **Deadline** (date): The deadline for completing the task.
- **UserID** (int): A foreign key that references `User_id` in the `User` table, linking each task to a user.
