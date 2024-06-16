# Buildings App

Hi. 

To run project you should run command:

```bash
docker compose up
```

To get access to the main page you should go to
[localhost:8184/]( http://localhost:8184/).

You will see the login page.
You can choose two roles:
1. User (username : "user" , password: "userPass").
2. Admin ((username : "admin" , password: "adminPass"))

The main difference between roles that the admin can delete records from DB. 

The main page contains first 20 records of the DB.

To add a new bulding go to "Add building" link. Fill in all fields and push the button "Add building". 

To remove building go to "Delete building". You should write a correct building id to remove it from the BD.
Additionally you should have the admin role (Use the admin credentials).

To update building you should fill in the id and one or several fields that you want to update.

To change the role you should log out using [localhost:8184/logout](http://localhost:8184/logout)