<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verify OTP</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/verifyOtp" method="post">
        <input type="hidden" name="id" value="${user.id}" />
        <input type="text" name="otp" placeholder="Enter OTP" required />
        <button type="submit">Verify OTP</button>
    </form>
    <div>
        <p style="color:red;">${error}</p>
    </div>
</body>
</html>
