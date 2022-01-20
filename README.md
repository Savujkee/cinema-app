## Cinema

This REST web application implements a backend part of a cinema ticket shop.

### Capabilities:

- register new users (with email and password)
- user authentication with email and password
- authorisation is based on user Role (USER and ADMIN by default) 
- USER can view information about available movies, movie sessions and search for sessions at a specific date; add tickets to his shopping cart, complete orders and view his orders history
- ADMIN can view and update data about cinema halls, movies and movie sessions, as well as view specific users' info. 

-----------------------------------  

### Implementation details:

- 3-tier architecture:
    - DAO
    - services
    - controllers

________________

### Technologies used:

- Hibernate
- Spring, Spring MVC, Spring Security
- Maven
- MySQL
- JSON
- Tomcat

--------------

### How to run the app:

1. Clone this code to your local repository
2. Configure Apache Tomcat for your IDE
3. Configure MySQL
4. Create a DB schema
5. Specify DB properties in *db.properties* file
6. Start application
7. Use Postman or similar tool to send HTTP requests
