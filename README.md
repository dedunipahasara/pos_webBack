POS System API Documentation

Base URL: http://localhost:8080/pos_webBack
Overview

The POS System API enables clients to manage orders, customers, and items within a point-of-sale system. The API supports various operations, including creating, retrieving, updating, and deleting orders, as well as managing items and customer information.
Endpoints
Orders
Get All Orders

    Method: GET
    URL: /orders
    Description: Retrieves all orders from the database.
    Response:

    json

    [
      {
        "orderId": "O00-001",
        "customerId": "C00-001",
        "customerName": "thamasha",
        "items": [
          {
            "itemId": "I00-001",
            "itemName": "Item 1",
            "quantity": 2,
            "price": 580.00
          }
        ],
        "total": 1000.00,
        "date": "2024-08-28"
      },
      ...
    ]

Create Order

    Method: POST
    URL: /order
    Description: Create a new order.
    Request Body:

    json

{
  "orderId": "O00-002",
  "customerId": "C00-002",
  "itemDtoList": [
    {
      "itemId": "I00-001",
      "quantity": 2,
      "price": 350.00
    }
  ],
  "total": 1000.00,
  "date": "2024-08-25"
}

Response:

json

    {
      "status": "Order created successfully"
    }

Retrieve Order

    Method: GET
    URL: /orders/{orderId}
    Description: Retrieve details of a specific order by ID.
    Response:

    json

    {
      "orderId": "O001",
      "customerId": "C00-001",
      "customerName": "thamasha",
      "items": [
        {
          "itemId": "I00-001",
          "itemName": "Item 1",
          "quantity": 2,
          "price": 580.00
        }
      ],
      "total": 1000.00,
      "date": "2024-08-28"
    }

Update Order

    Method: PUT
    URL: /orders/{orderId}
    Description: Update an existing order by ID.
    Request Body:

    json

{
  "customerId": "C00-001",
  "itemDtoList": [
    {
      "itemId": "I00-001",
      "quantity": 3,
      "price": 500.00
    }
  ],
  "total": 1500.00,
  "date": "2024-08-28"
}

Response:

json

    {
      "status": "Order updated successfully"
    }

Delete Order

    Method: DELETE
    URL: /orders/{orderId}
    Description: Delete a specific order by ID.
    Response:

    json

    {
      "status": "Order deleted successfully"
    }

Items
Create Item

    Method: POST
    URL: /item
    Description: Add a new item to the inventory.
    Request Body:

    json

{
  "itemId": "I00-001",
  "itemName": "Item 2",
  "price": 750.00,
  "quantity": 50,
  "description": "Item 2 description"
}

Response:

json

    {
      "status": "Item added successfully"
    }

Retrieve Item

    Method: GET
    URL: /item/{itemId}
    Description: Retrieve details of a specific item by ID.
    Response:

    json

    {
      "itemId": "I00-001",
      "itemName": "Item 1",
      "price": 500.00,
      "quantity": 100,
      "description": "Item 1 description"
    }

Update Item

    Method: PUT
    URL: /item/{itemId}
    Description: Update details of an existing item by ID.
    Request Body:

    json

{
  "itemName": "Updated Item Name",
  "price": 800.00,
  "quantity": 75,
  "description": "Updated description"
}

Response:

json

    {
      "status": "Item updated successfully"
    }

Delete Item

    Method: DELETE
    URL: /item/{itemId}
    Description: Remove a specific item from the inventory by ID.
    Response:

    json

    {
      "status": "Item deleted successfully"
    }

Customers
Create Customer

    Method: POST
    URL: /customer
    Description: Add a new customer.
    Request Body:

    json

{
  "customerId": "C00-002",
  "customerName": "thamasha",
  "email": "thama@gmail.com",
  "phone": "687977990"
}

Response:

json

    {
      "status": "Customer added successfully"
    }

Retrieve Customer

    Method: GET
    URL: /customer/{customerId}
    Description: Retrieve details of a specific customer by ID.
    Response:

    json

    {
      "customerId": "C00-002",
      "customerName": "thamasha",
      "email": "thama@gmail.com",
      "phone": "687977990"
    }

Update Customer

    Method: PUT
    URL: /customer/{customerId}
    Description: Update information of an existing customer by ID.
    Request Body:

    json

{
  "customerName": "thamasha",
  "email": "thama@gmail.com",
  "phone": "568980655"
}

Response:

json

    {
      "status": "Customer updated successfully"
    }

Delete Customer

    Method: DELETE
    URL: /customer/{customerId}
    Description: Remove a specific customer by ID.
    Response:

    json

    {
      "status": "Customer deleted successfully"
    }

Error Handling

The API returns the following HTTP status codes and error messages when an error occurs:

    400 Bad Request: The server could not understand the request due to invalid syntax or missing required parameters.
    404 Not Found: The requested resource could not be found on the server.
    500 Internal Server Error: The server encountered an unexpected condition that prevented it from fulfilling the request.
