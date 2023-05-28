import React, { useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
import { useNavigate, useParams, useLocation } from "react-router-dom";

const BoardEdit = ({ match, history }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const initialboard = location.state.board;
  const [validated, setValidated] = useState(false);

  const [board, setBoard] = useState({
    title: "",
    content: "",
  });

  const setField = (field, value) => {
    setBoard({
      ...board,
      [field]: value,
    });
  };

  const { id } = useParams();

  useEffect(() => {
    console.log(initialboard);
    setBoard({
      title: initialboard.title,
      content: initialboard.content,
    });
  }, []);

  const handleSubmit = (event) => {
    // event.preventDefault();
    // event.stopPropagation();
    // const form = event.currentTarget;
    // if (!form.checkValidity()) {
    //   setValidated(false);
    //   return;
    // }
    // setValidated(true);
    // const updatedBoard = {
    //   id: id,
    //   title: form.titleInput.value,
    //   content: form.contentText.value,
    // };
    // updateBoard(updatedBoard);
    // navigate("/add");
  };

  const updateBoard = async (board) => {
    // const res = await axios.put("/api/board", board);
    // console.log(res);
    // navigate("/");
  };

  return (
    <div className="container">
      <Form noValidate validated={validated} onSubmit={handleSubmit}>
        <Form.Group controlId="titleInput">
          <Form.Label>제목</Form.Label>
          <Form.Control
            required
            placeholder=""
            value={board.title}
            onChange={(e) => setField("title", e.target.value)}
          />
          <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
          <Form.Control.Feedback type="invalid">
            제목을 입력하세요!!
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group controlId="contentText">
          <Form.Label>내용</Form.Label>
          <Form.Control
            required
            as="textarea"
            rows={20}
            value={board.content}
            onChange={(e) => setField("content", e.target.value)}
          />
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

export default BoardEdit;
