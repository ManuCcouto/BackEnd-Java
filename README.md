# Backend Access Test

## Solution Overview

In this section, we'll detail the solution implemented to address the requirements of the backend access test.

## Architecture Used

To structure the application, we opted to implement a hexagonal architecture, which promotes clear separation of responsibilities and modularity within the system. This choice facilitates code maintenance, scalability, and testing.

## Implemented Operations

### Get Related Sagas

The endpoint for user interaction is:

```bash
GET /game/{gameId}/saga
 ```
Users can access this endpoint by providing a specific game ID in the URL to retrieve related sagas for that game.

### Get Available Video Game

The endpoint for user interaction is:
```bash
GET /game/{gameId}/saga
 ```

## Consuming Queue Events and Updating Stock

In addition to the mentioned operations, the application also consumes events from a real-time queue to keep the video game stock updated. This functionality ensures that stock information is always synchronized and available to users.
## Project Developers

This project was developed by Manuel Comesaña Couto.
