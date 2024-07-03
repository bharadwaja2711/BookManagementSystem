<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Management</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<style>
    body {
        padding-top: 160px; /* Increase padding to match larger navbar, slogan, and search bar */
        background-size: cover; /* Cover the entire page */
        background-position: center; /* Center the image */
        background-repeat: no-repeat; /* Do not repeat the image */
        background-attachment: fixed; /* Fix the background image */
        color: #ffffff; /* Text color to contrast with the background image */
    }
    .completed {
        color: green;
    }
    .pending {
        color: red;
    }
    /* Custom navbar styles */
    .navbar-custom {
        background-color: rgba(0, 121, 107, 0.8); /* Teal color with transparency */
        height: 80px; /* Increased height for a larger navbar */
        transition: background-color 0.3s, height 0.3s;
        border-radius: 0 0 20px 20px; /* Curvy edges */
        box-shadow: none !important; /* Remove box-shadow */
    }
    .navbar-custom .navbar-brand,
    .navbar-custom .nav-link {
        color: #ffffff;
        font-size: 1.2rem; /* Larger font size */
        transition: color 0.3s;
    }
    .navbar-custom .nav-link {
        position: relative;
        overflow: hidden;
    }
    .navbar-custom .nav-link::before {
        content: '';
        position: absolute;
        width: 100%;
        height: 3px;
        bottom: 0;
        left: -100%;
        background-color: #ffffff;
        transition: left 0.3s;
    }
    .navbar-custom .nav-link:hover::before {
        left: 0;
    }
    .navbar-custom .nav-link:hover {
        color: #aed581; /* Light green color on hover */
    }
    .navbar-custom .navbar-toggler-icon {
        background-image: url("data:image/svg+xml;charset=utf8,%3Csvg viewBox='0 0 30 30' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath stroke='rgba%28255, 255, 255, 0.5%29' stroke-width='2' stroke-linecap='round' stroke-miterlimit='10' d='M4 7h22M4 15h22M4 23h22'/%3E%3C/svg%3E");
    }
    .navbar-custom .dropdown-menu {
        background-color: rgba(0, 121, 107, 0.8); /* Transparent dropdown */
        border: none;
        border-radius: 0 0 20px 20px; /* Curvy edges */
    }
    .navbar-custom .dropdown-item {
        color: #ffffff;
        transition: background-color 0.3s, color 0.3s;
    }
    .navbar-custom .dropdown-item:hover {
        background-color: #004d40; /* Darker teal color */
        color: #aed581;
    }
    /* Navbar background change on scroll */
    .navbar-custom.scrolled {
        background-color: rgba(0, 77, 64, 0.8); /* Darker teal with transparency */
    }
    .slogan {
        text-align: center;
        font-size: 2rem; /* Increased font size */
        margin-top: 10px; /* Adjust margin to align with navbar */
        color: #ffffff; /* Set the slogan color to black */
        font-weight: bold; /* Bold font weight */
        text-transform: uppercase; /* Convert text to uppercase */
        font-family: 'Helvetica Neue', 'Helvetica', 'Arial', sans-serif; /* Set font family */
    }
    .search-bar-container {
        display: flex;
        justify-content: center;
        margin-top: 20px; /* Adjust margin to create space below the slogan */
    }
    .search-bar {
        width: 60%; /* Increase width */
        position: relative;
    }
    .search-bar input {
        width: 100%;
        padding: 20px 25px; /* Increase vertical padding */
        border-radius: 50px;
        border: 1px solid #cccccc;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        transition: box-shadow 0.3s, transform 0.3s;
        font-size: 1.2rem; /* Increase font size */
    }
    .search-bar input:focus {
        outline: none;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        transform: translateY(-2px);
    }
    .featured-books {
        margin-top: 40px; /* Adjust margin for spacing */
        padding-left: 20px; /* Left alignment for the text */
        font-size: 1.3rem;
        font-weight: bold;
    }
    .card-container {
        display: flex;
        justify-content: space-around;
        margin-top: 20px;
    }
    .card {
        width: 250px;
        background-color: #ffffff;
        border-radius: 10px;
        margin: 10px;
        transition: transform 0.3s ease-in-out;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        height: 320px; /* Adjusted height for each card */
        cursor: pointer; /* Change cursor to pointer when hovering over the card */
    }
    .card:hover {
        transform: translateY(-10px);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
    }
    .card img {
        height: 100%;
        width: 100%;
        object-fit: cover;
        border-radius: 10px;
    }
    .footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: rgba(0, 121, 107, 0.8);
        color: white;
        text-align: center;
        padding: 5px 0; /* Reduce padding for smaller footer */
        font-size: 0.9rem; /* Smaller font size */
    }
    .footer .text-muted {
        color: white; /* Set text color to white */
    }
    .modal-backdrop {
        backdrop-filter: blur(5px); /* Apply blur to the backdrop */
    }
    .modal-content {
        color: #000000; /* Set modal text color to black */
    }
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-custom fixed-top">
    <a class="navbar-brand" href="/">Book Management</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    More
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/about">About</a>
                    <a class="dropdown-item" href="/contact">Contact</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><i class="fas fa-user"></i> Login/Signup</a>
            </li>
        </ul>
    </div>
</nav>

<footer class="footer py-2 bg-dark" style="font-size: 0.8rem;">
    <div class="container text-center">
        <span class="text-white font-weight-bold">&copy; 2024 Book Management System. All rights reserved.</span>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
