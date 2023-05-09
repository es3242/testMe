import "./Register.css";

function Register() {
  return (
    <div>
      <h1>Registration Form</h1>
      <form method="post" action="/register">
        <label for="nickname">Nickname:</label>
        <input type="text" name="nickname" id="nickname" required />
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required />
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required />
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default Register;
