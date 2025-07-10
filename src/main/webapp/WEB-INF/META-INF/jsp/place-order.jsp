<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Place Order</title>
    <script>
        let productPrices = {};

        // Called after page load
        function setup() {
            // Build product-price map from embedded data
            <c:forEach var="product" items="${products}">
                productPrices[${product.productId}] = ${product.price};
            </c:forEach>

            // Set up listeners
            document.getElementById("productId").addEventListener("change", calculateAmount);
            document.getElementById("quantity").addEventListener("input", calculateAmount);
        }

        function calculateAmount() {
            const productId = document.getElementById("productId").value;
            const quantity = parseInt(document.getElementById("quantity").value) || 0;

            const price = productPrices[productId] || 0;
            const amount = price * quantity;

            document.getElementById("amount").value = amount.toFixed(2);
        }

        window.onload = setup;
    </script>
</head>
<body>

<h2>Available Products</h2>
<table border="1">
    <tr>
        <th>ID</th><th>Name</th><th>Price</th><th>Available Quantity</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.productId}</td>
            <td>${product.productName}</td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
        </tr>
    </c:forEach>
</table>

<h3>Place an Order</h3>
<form method="post" action="/place-order">
    Product ID:
    <select name="productId" id="productId" required>
        <option value="">-- Select Product --</option>
        <c:forEach var="product" items="${products}">
            <option value="${product.productId}">${product.productId} - ${product.productName}</option>
        </c:forEach>
    </select>
    <br/>

    Quantity: <input type="number" name="quantity" id="quantity" required><br/>
    Customer Id: <input type="number" name="customerId" required><br/>
    Amount: <input type="number" name = "amount" id="amount" readonly><br/>
    <button type="submit">Place Order</button>
</form>

<c:if test="${not empty message}">
    <p><strong>${message}</strong></p>
</c:if>

</body>
</html>
