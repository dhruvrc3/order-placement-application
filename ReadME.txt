1. Overview of the project :
	This is a simple Order Placing and Processing web application through which user can place an online order of products. 
	And this application keep track of stock of order and maintains live stock. Applciation also stores payment details and order details.
	
2. Technologies and frameworks used, along with their versions  :	
	- Java 17
	- Spring Boot 3.5.3
	- Spring Data JPA
	- H2 In-Memory Database
	- JSP for View Layer
	- Maven
	
3. Functional Flow :
	1. Customer selects a product and quantity.
	2. Application checks product availability.
	3. Locks stock and simulates payment.
	4. If payment succeeds, order is saved.
	5. If payment fails, stock is released.

4. Components :
	- OrderController: Handles form submission
	- OrderProcessService: Core business logic
	- PaymentService: Simulates payment
	- Repositories: JPA data access
	- Entities : ProductDetails, OrderDetails, PaymentDetails
	- Helper Dto : PaymentDto : to help pass the data

5. Sample Request
	- Form URL: '/place-order'
	- Method: POST
	- Params: productId, quantity, customerId, amount