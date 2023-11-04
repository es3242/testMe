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
import "./editChoice.scss";

function EditChoice() {
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
    navigate("/allquestion");
  }

  const [loremIpsum, setLoremIpsum] = useState(
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eget enim quis enim convallis iaculis. Donec semper metus sit amet mi tempor, eget semper justo iaculis. Nullam mattis enim eget quam egestas, non rutrum enim tristique. Donec sed luctus magna. Duis cursus semper ullamcorper. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;"
  );

  const [pdfText, setPdfText] = useState(
    "보조기억장치\n외부 기억장치\n반영구적으로 데이터를 저장하고 보존\n중앙처리장치와 직접 정보를 교환할 수 없기 때문에 주기억장치로 옮겨진 후 처리\n주기억장치에 비해 가격은 저렴하고 저장 용량 또한 크지만 속도가 느린 단점\n자기테이프, 자기디스크, CD-ROM, DVD, Flash Memory, 광디스크"
  );

  const [questionTitle, setQuestionTitle] = useState("");
  const [wrongAnswer1, setWrongAnswer1] = useState("");
  const [wrongAnswer2, setWrongAnswer2] = useState("");
  const [wrongAnswer3, setWrongAnswer3] = useState("");
  const [wrongAnswer4, setWrongAnswer4] = useState("");
  const [wrongAnswer5, setWrongAnswer5] = useState("");
  const [answer, setAnswer] = useState("");

  const [formData, setFormData] = useState({
    title: "",
    content: "",
  });

  useEffect(() => {
    setFormData({
      title: "다음 중 보조기억장치에 대한 설명으로 맞지 않은 것은?",
      content: "여기에 문제 내용 입력",
    });
  }, []);

  // 서버로부터 전달받은 값을 state에 저장한 다음
  // placeholder에 전달해준다
  // 만약 값이 비어있다면 placeholder로 들어갔던 state를 그대로 쓴다.
  // 이렇게 함으로서 state의 값을 바꿔주는 것만으로
  return (
    <>
      <div className="container">
        <Row className="p-10">
          <div className="col-md-6 row mt-4 rounded border border-success p-3">
            <div className="pdfText">{pdfText}</div>
          </div>
          <div className="col-md-6 mt-3">
            <Form onSubmit={handleSubmit}>
              <FormGroup controlId="title" className="mx-3 mb-3">
                <Form.Label for="title">문제 제목</Form.Label>
                <Form.Control
                  type="text"
                  id="title"
                  placeholder={formData.title}
                  onChange={handleChange}
                />
              </FormGroup>
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
                    placeholder="주기억장치에 비해서 가격이 비싸다"
                  />
                </Col>
              </Form.Group>
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
                    placeholder="중앙처리장치와 직접 정보를 교환할 수 없다"
                  />
                </Col>
              </Form.Group>
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
                    placeholder="데이터를 반영구적으로 저장할 수 있다"
                  />
                </Col>
              </Form.Group>
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
                    placeholder="하드디스크와 SSD는 보조기억장치이다"
                  />
                </Col>
              </Form.Group>
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
                    placeholder="주기억장치 대비 저장 용량이 크다"
                  />
                </Col>
              </Form.Group>

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
        </Row>
      </div>
    </>
  );
}

export default EditChoice;
