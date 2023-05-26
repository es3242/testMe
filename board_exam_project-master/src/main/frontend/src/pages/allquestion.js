import React, { useState, useEffect } from "react";
import {
  Container,
  Row,
  Col,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
} from "react-bootstrap";

function AllQuestion() {
  function handleChange(event) {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  }

  function handleSubmit(event) {
    event.preventDefault();
    console.log(formData);
  }

  const [loremIpsum, setLoremIpsum] = useState(
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eget enim quis enim convallis iaculis. Donec semper metus sit amet mi tempor, eget semper justo iaculis. Nullam mattis enim eget quam egestas, non rutrum enim tristique. Donec sed luctus magna. Duis cursus semper ullamcorper. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;"
  );

  const [formData, setFormData] = useState({
    title: "",
    content: "",
  });

  useEffect(() => {
    setFormData({
      title: "여기에 문제 제목 입력",
      content: "여기에 문제 내용 입력",
    });
  }, []);

  // 사용법
  // <div className="mt-3 rounded border border-success p-3">
  // 요놈을 컴포넌트화시킨 다음, 문제, 정답을 props로 보내주면 됨
  // 문제가 여러 개이기 때문에, map 반복문을 사용해서 문제 수만큼 띄울 것
  return (
    <div className="container">
      <div className="mt-3 rounded border border-success p-3">
        <Form onSubmit={handleSubmit}>
          <FormGroup controlId="title" className="mx-3 mb-3">
            <Form.Label for="title">문제 제목</Form.Label>
            <Form.Control
              type="text"
              id="title"
              value={formData.title}
              onChange={handleChange}
            />
          </FormGroup>
          <FormGroup controlId="content" className="mb-3 mx-3">
            <Form.Label for="content">문제 내용</Form.Label>
            <Form.Control
              type="text"
              as="textarea"
              id="title"
              value={formData.content}
              rows="10"
              cols="30"
              onChange={handleChange}
            />
          </FormGroup>
          <FormGroup controlId="title" className="mx-3 mb-3">
            <Form.Label for="title">정답</Form.Label>
            <Form.Control
              type="text"
              id="title"
              value={formData.title}
              onChange={handleChange}
            />
          </FormGroup>
          <Button variant="primary" type="submit" className="mx-3">
            확인
          </Button>
        </Form>
      </div>
    </div>
  );
}

export default AllQuestion;
