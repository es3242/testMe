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
import { useNavigate } from "react-router-dom";

function AllQuestion() {
  const navigate = useNavigate();
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
      title:
        '논리 게이트 크기를 줄이고, 더 많은 게이트를 더욱 조밀하게 넣고 ""',
      content: "클럭 속도를 높인다",
    });
  }, []);

  function handleSubmit(event) {
    event.preventDefault();
    navigate("/makecomplete");
  }

  // 사용법
  // <div className="mt-3 rounded border border-success p-3">
  // 요놈을 컴포넌트화시킨 다음, 문제, 정답을 props로 보내주면 됨
  // 문제가 여러 개이기 때문에, map 반복문을 사용해서 문제 수만큼 띄울 것
  return (
    <div className="container">
      <div className="mt-3 rounded border border-success p-3">
        <Form onSubmit={handleSubmit}>
          <FormGroup controlId="title" className="mx-3 mb-3">
            <Form.Label for="title">문제</Form.Label>
            <Form.Control
              type="text"
              as="textarea"
              id="title"
              placeholder={formData.title}
              onChange={handleChange}
            />
          </FormGroup>
          <FormGroup controlId="title" className="mx-3 mb-3">
            <Form.Label for="title">정답</Form.Label>
            <Form.Control
              type="text"
              id="title"
              placeholder={formData.content}
              onChange={handleChange}
            />
          </FormGroup>
          <Button
            variant="primary"
            type="submit"
            className="mx-6"
            onClick={handleSubmit}
          >
            확인
          </Button>
        </Form>
      </div>
    </div>
  );
}

export default AllQuestion;
