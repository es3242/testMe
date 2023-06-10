import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const BoardRegister = (props) => {
  const navigate = useNavigate();
  const [user, setUser] = useState(sessionStorage.getItem("loggedIn"));
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [contentAt, setContentAt] = useState(new Date().toLocaleString());

  const handleSubmit = async (event) => {
    event.preventDefault();
    event.stopPropagation();

    console.log(user);
    console.log(title);
    console.log(content);
    console.log(contentAt);

    const postData = { user, title, content, contentAt };
    try {
      const response = await axios.post("/save", postData);
      if (response.status === 200) {
        window.location.href = "/list";
      }
    } catch (error) {
      console.error("Error while sending data:", error);
    }

    // navigate("/board");
  };

  return (
    <div className="container">
      <h1 className="d-flex justify-content-center mt-3 mb-3">글 쓰기</h1>
      <Form
        method="post"
        action="/save"
        object="freeboard"
        onSubmit={handleSubmit}
      >
        <Form.Group controlId="userID">
          <Form.Label htmlFor="user">현재 로그인한 ID</Form.Label>
          <Form.Control
            required
            readOnly
            type="text"
            id="user"
            name="user"
            value={user}
          />
        </Form.Group>
        <Form.Group controlId="titleInput">
          <Form.Label htmlFor="title">제목</Form.Label>
          <Form.Control
            required
            type="text"
            name="title"
            id="title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder=""
          />
        </Form.Group>
        <Form.Group controlId="contentText">
          <Form.Label htmlFor="content">내용</Form.Label>
          <Form.Control
            required
            as="textarea"
            name="content"
            rows={20}
            value={content}
            onChange={(e) => setContent(e.target.value)}
            id="content"
          />
        </Form.Group>
        <input type="hidden" value={contentAt} name="contentAt"></input>
        <Button variant="primary" type="submit">
          저장
        </Button>
      </Form>
    </div>
  );
};

export default BoardRegister;
