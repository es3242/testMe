import "./Register.css";
import { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useNavigate } from "react-router-dom";

function Register() {
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    // Perform registration logic here
    // Example: Send registration request to the server

    // Assuming the registration is successful, update the message
    alert("회원가입에 성공했습니다!");
    // 홈으로 넘기기
    navigate("/");
  };

  return (
    <div className="container mt-3">
      <h1 className="d-flex justify-content-center mt-7 mb-3">회원가입</h1>
      <Form method="post" action="/register" onSubmit={handleSubmit}>
        <Form.Group className="mb-3" controlId="formBasicNickname">
          <Form.Label>닉네임</Form.Label>
          <Form.Control
            type="text"
            name="nickname"
            placeholder="닉네임을 입력하세요"
            required
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>이메일</Form.Label>
          <Form.Control
            type="email"
            name="email"
            placeholder="이메일을 입력하세요"
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
          회원가입
        </Button>
      </Form>
    </div>
  );
}

export default Register;
