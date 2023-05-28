import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

function Login() {
  return (
    <div>
      <div className="container mt-3">
        <h1 className="d-flex justify-content-center mt-7 mb-3">로그인</h1>
        <Form method="post" action="/login">
          <Form.Group className="mb-3" controlId="formBasicID">
            <Form.Label>ID</Form.Label>
            <Form.Control
              type="text"
              name="id"
              placeholder="ID를 입력하세요"
              required
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>비밀번호</Form.Label>
            <Form.Control
              type="password"
              name="password"
              placeholder="비밀번호를 입력하세요"
              className=""
              required
            />
          </Form.Group>
          <Button variant="primary" type="submit">
            로그인
          </Button>
        </Form>
      </div>
    </div>
  );
}

export default Login;
