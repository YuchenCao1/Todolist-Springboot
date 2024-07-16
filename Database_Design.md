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
- **UserID** (int): A foreign key that references `User_id` in the `User` table, linking each task to a user.
- **Title** (varchar): The title of the task.
- **Description** (text): A detailed description of the task.
- **DueDate** (date): The deadline for completing the task.
- **Status** (varchar): The current status of the task (e.g., pending, completed).
