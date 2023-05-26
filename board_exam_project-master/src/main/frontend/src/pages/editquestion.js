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

function EditQuestion() {
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

  return (
    <>
      <div className="container d-flex">
        <Row className="p-10">
          <div className="col-md-6 row mt-4 rounded border border-success p-3">
            <div className="lorem-ipsum">{loremIpsum}</div>
          </div>
          <div className="col-md-6 mt-3">
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
        </Row>
      </div>
      <div className="container d-flex">
        <Row className="p-10">
          <div className="col-md-6 row mt-4 rounded border border-success p-3">
            <div className="lorem-ipsum">{loremIpsum}</div>
          </div>
          <div className="col-md-6 mt-3">
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
              <Form.Group
                as={Row}
                className="mb-3 mx-3"
                controlId="formHorizontalEmail"
              >
                <Form.Label column sm={3}>
                  오답
                </Form.Label>
                <Col sm={8}>
                  <Form.Control
                    type="text"
                    className="mx-3"
                    placeholder="오답 입력"
                  />
                </Col>
              </Form.Group>
              <Form.Group
                as={Row}
                className="mb-3 mx-3"
                controlId="formHorizontalEmail"
              >
                <Form.Label column sm={3}>
                  정답
                </Form.Label>
                <Col sm={8}>
                  <Form.Control
                    type="text"
                    className="mx-3"
                    placeholder="정답 입력"
                  />
                </Col>
              </Form.Group>
              <Form.Group
                as={Row}
                className="mb-3 mx-3"
                controlId="formHorizontalEmail"
              >
                <Form.Label column sm={3}>
                  정답
                </Form.Label>
                <Col sm={8}>
                  <Form.Control
                    type="text"
                    className="mx-3"
                    placeholder="정답 입력"
                  />
                </Col>
              </Form.Group>
              <Form.Group
                as={Row}
                className="mb-3 mx-3"
                controlId="formHorizontalEmail"
              >
                <Form.Label column sm={3}>
                  정답
                </Form.Label>
                <Col sm={8}>
                  <Form.Control
                    type="text"
                    className="mx-3"
                    placeholder="정답 입력"
                  />
                </Col>
              </Form.Group>
              <Form.Group
                as={Row}
                className="mb-3 mx-3"
                controlId="formHorizontalEmail"
              >
                <Form.Label column sm={3}>
                  정답
                </Form.Label>
                <Col sm={8}>
                  <Form.Control
                    type="text"
                    className="mx-3"
                    placeholder="정답 입력"
                  />
                </Col>
              </Form.Group>

              <Button variant="primary" type="submit" className="mx-3">
                확인
              </Button>
            </Form>
          </div>
        </Row>
      </div>
    </>
  );
}

export default EditQuestion;
