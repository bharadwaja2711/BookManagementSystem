<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <!-- Form fields for user details -->
        <input type="text" name="surname" placeholder="Surname" required />
        <input type="text" name="givenName" placeholder="Given Name" required />
        <input type="text" name="dob" placeholder="Date of Birth (YYYY-MM-DD)" required />
        <input type="text" name="gender" placeholder="Gender" required />
        <input type="email" name="email" placeholder="Email" required />
        <input type="password" name="password" placeholder="Password" required />
        <input type="text" name="phoneNo" placeholder="Phone Number" required />
        <input type="text" name="address" placeholder="Address" required />

        <!-- CAPTCHA input fields -->
        <input type="text" name="captcha" placeholder="Enter CAPTCHA" required />
        <img src="${captchaImage}" alt="CAPTCHA">

        <button type="submit">Register</button>
    </form>
    <div>
        <p style="color:red;">${error}</p>
    </div>
</body>
</html>
