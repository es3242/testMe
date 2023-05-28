import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const BoardRegister = (props) => {
  const navigate = useNavigate();
  const [validated, setValidated] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    event.stopPropagation();

    const form = event.currentTarget;
    if (!form.checkValidity()) {
      setValidated(false);
      return;
    }

    setValidated(true);

    const board = {
      title: form.titleInput.value,
      content: form.contentText.value,
    };
    addBoard(board);
    navigate("/add");
  };

  const addBoard = async (board) => {
    const res = await axios.post("/api/board", board);
    console.log(res);
  };

  return (
    <div className="container">
      <Form noValidate validated={validated} onSubmit={handleSubmit}>
        <Form.Group controlId="titleInput">
          <Form.Label>제목</Form.Label>
          <Form.Control required type="email" placeholder="" />
          <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
          <Form.Control.Feedback type="invalid">
            이메일을 입력하세요!!
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group controlId="contentText">
          <Form.Label>내용</Form.Label>
          <Form.Control required as="textarea" rows={20} />
          <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
          <Form.Control.Feedback type="invalid">
            내용을 입력하세요!!
          </Form.Control.Feedback>
        </Form.Group>
        <Button variant="primary" type="submit">
          저장
        </Button>
      </Form>
    </div>
  );
};

export default BoardRegister;
