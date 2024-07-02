<%@ page language="java" contentType="charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 70px;
        }
        .todo-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .completed {
            color: green;
        }
        .pending {
            color: red;
        }
        .table-custom {
        border-collapse: collapse;
        width: 100%;
        border: 2px solid #ddd; /* Adjust border thickness */
    }
    .table-custom th, .table-custom td {
        padding: 8px;
        text-align: left;
        border: 2px solid #ddd; /* Adjust border thickness */
    }
    .table-custom th {
        background-color: #f2f2f2; /* Light gray background for headers */
    }
    .table-custom tbody tr:nth-child(even) {
        background-color: #f9f9f9; /* Alternate row background */
    }
    .table-custom tbody tr:hover {
        background-color: #f2f2f2; /* Hover color */
    }
    </style>
</head>
    
<body>
	<jsp:include page="index.jsp" />
    <div class="container">
        <h1 class="mb-4">Book List</h1>
        <table class="table table-custom">
    <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="updateBook(${book.id})">Update</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteBook(${book.id})">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
