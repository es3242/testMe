function Login() {
  return (
    <div>
      <h1>Login Form</h1>
      <form method="post" action="/login">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required />
        <br />
        <br />
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required />
        <br />
        <br />
        <input type="submit" value="Login" />
      </form>
    </div>
  );
}

export default Login;
