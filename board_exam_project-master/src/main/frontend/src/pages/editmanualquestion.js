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
import EditSubjective from "../components/editSubjective";
import EditChoice from "../components/editChoice";
import { useNavigate } from "react-router-dom";
import "./editmanualquestion.scss";
import axios from "axios";

function EditManualQuestion() {
  const [numberOfPages, setNumberOfPages] = useState(0);
  const [pageText, setPageText] = useState([]);
  const [questionTitle, setQuestionTitle] = useState([]);
  const [questionDescription, setQuestionDescription] = useState([]);
  const [questionAnswer, setQuestionAnswer] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    getQuestionList();
  }, []);

  const getQuestionList = async () => {
    const res = await axios
      .get("/display2")
      .then(function (response) {
        console.log(response);
        setNumberOfPages(response.data.numberOfPages);
        setPageText(response.data.pageTexts);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  };

  /*
  const [questionTitle, setQuestionTitle] = useState([]);
  const [questionDescription, setQuestionDescription] = useState([]);
  const [questionAnswer, setQuestionAnswer] = useState([]);
  */

  // function updateQuestionTitle(index, newValue) {
  //   setQuestionTitle((prevQuestionTitle) =>
  //     prevQuestionTitle.map((value, currentIndex) =>
  //       currentIndex === index ? newValue : value
  //     )
  //   );
  // }

  function handleTitleChange(event, index) {
    const { id, value } = event.target;
    console.log(id);
    console.log(value);

    const newQuestionTitle = [...questionTitle];
    newQuestionTitle[index] = event.target.value;
    setQuestionTitle(newQuestionTitle);
  }

  function handleChange(event, currentIndex) {
    const { id, value } = event.target;
    console.log(id);
    console.log(currentIndex);
    console.log(value);

    // if (id === "title") {
    //   updateQuestionTitle(currentIndex, value);
    // }
    //   ...formData,
    //   [name]: value,
    // });
  }

  function handleSubmit(event) {
    event.preventDefault();
    console.log(formData);
    navigate("/allquestion");
  }

  // const [questionTitle, setQuestionTitle] = useState("");
  // const [wrongAnswer1, setWrongAnswer1] = useState("");
  // const [wrongAnswer2, setWrongAnswer2] = useState("");
  // const [wrongAnswer3, setWrongAnswer3] = useState("");
  // const [wrongAnswer4, setWrongAnswer4] = useState("");
  // const [wrongAnswer5, setWrongAnswer5] = useState("");
  // const [answer, setAnswer] = useState("");

  const [formData, setFormData] = useState({
    title: "",
    content: "",
  });

  // useEffect(() => {
  //   setFormData({
  //     title: "다음 중 보조기억장치에 대한 설명으로 맞지 않은 것은?",
  //     content: "여기에 문제 내용 입력",
  //   });
  // }, []);

  // 서버로부터 전달받은 값을 state에 저장한 다음
  // placeholder에 전달해준다
  // 만약 값이 비어있다면 placeholder로 들어갔던 state를 그대로 쓴다.
  // 이렇게 함으로서 state의 값을 바꿔주는 것만으로
  return (
    <>
      <div className="container">
        <Row className="p-10">
          {pageText.map((pageText, index) => (
            <Row key={index} className="p-10">
              <div className="col-md-6 row mt-4 rounded border border-success p-3">
                {pageText}
              </div>

              <div className="col-md-6 mt-3">
                <FormGroup controlId="title" className="mx-3 mb-3">
                  <Form.Label for="title">
                    페이지 {index + 1} 문제 제목
                  </Form.Label>
                  <Form.Control
                    type="text"
                    id="title"
                    key={index}
                    onChange={(event) => handleTitleChange(event, index)}
                  />
                </FormGroup>
                <FormGroup controlId="title" className="mx-3 mb-3">
                  <Form.Label for="title">보기</Form.Label>
                  <Form.Control
                    as="textarea"
                    id="question"
                    key={index}
                    rows="5"
                    style={{ resize: "both" }}
                    onChange={(event) => handleChange(event, index)}
                  />
                </FormGroup>
                <FormGroup controlId="title" className="mx-3 mb-3">
                  <Form.Label for="title">정답</Form.Label>
                  <Form.Control
                    as="textarea"
                    id="answer"
                    key={index}
                    onChange={(event) => handleChange(event, index)}
                  />
                </FormGroup>
              </div>
            </Row>
          ))}
        </Row>
        <div className="d-flex justify-content-center">
          <Button
            variant="primary"
            type="submit"
            className="mx-6 mt-3"
            onClick={handleSubmit}
          >
            확인
          </Button>
        </div>
      </div>
    </>
  );

  return <div></div>;
}

export default EditManualQuestion;
