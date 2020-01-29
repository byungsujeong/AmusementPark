# Amusement Park Reservation System
https://youtu.be/uKNcZWVIfok

# Summary
The reservation system for rides through the reservation number of the free passes and the reservation number for amusement parks (free passes, entrance tickets, etc.)

# Languages
* Java(SWING)
* MySQL

# Tools
* Eclipse Jee
* DBeaver
* Draw.io
* Object Aid

# Features
* Log in and book your product through the Smart Booking menu
* Automatically generate reservation number for smart reservation
* Check your reservation details through the Smart Appointment Confirmation
* Manage products and bookings for all customers on administrator contact

# Trade-off decisions
In the initial model, we thought that if we had member table and ride table, we would be able to load the reserved data, but if we didn't create a separate table, we would have to create a table that uses the time table ID as the key value.

# Challenges
My team started without defining specific details about schema among team members. We shared same tables such as DTO, and DAO. I had to modify several times whenever we shared the interim results. To resolve the problem, I discussed with my team to redefine the schema properly.
