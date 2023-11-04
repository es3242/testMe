import React, { useState } from "react";
import logo from "../logo.svg";
import { useNavigate, useLocation } from "react-router-dom";
import pdfIcon from "../public/img1.png";

const QuestionForm = () => {
  // const logo = [icon]; // Add your desired icons here
  // const randomIcon = icons[Math.floor(Math.random() * icons.length)];
  const navigate = useNavigate();
  const location = useLocation();
  let fileName = location.state.fileName;

  console.log(location.state);

  // 라디오 버튼 선택값을 저장할 state 추가
  const [questionnaireType, setQuestionnaireType] = useState("");

  const handleSubmit = async () => {
    // 수동이냐 자동이냐에 따라서 다르게 라우팅
    if (questionnaireType === "manual") {
      navigate("/manualedit");
    } else if (questionnaireType === "automated") {
      navigate("/edit");
    }
  };

  // 라디오 버튼 변경 이벤트 처리 함수
  const handleRadioChange = (e) => {
    setQuestionnaireType(e.target.value);
  };

  return (
    <div className="container">
      <div className="d-flex row mt-4 justify-content-center rounded border border-success font-weight-bold p-3">
        <div className="col-6" style={{ width: "10rem", height: "10rem" }}>
          {/* Random icon */}
          <span className={`icon ${logo}`}>
            <img
              src={pdfIcon}
              alt="Another Image"
              style={{ width: "100%", height: "100%" }}
            />
          </span>
        </div>
        <div className="col-3 d-flex flex-column justify-content-center">
          {/* Large title */}
          {/* 더미데이터임. 나중에 Redux에 담을 State로 반영 필요 */}
          <h3>{fileName}</h3>
          {/* Small title */}
          {/* <h4>PDF 정보</h4> */}
        </div>
      </div>
      <div className="mt-4 d-flex justify-content-center">
        <div className="form-check form-check-inline d-flex align-items-center">
          <input
            className="form-check-input mx-2"
            type="radio"
            name="questionnaireType"
            id="manualQuestionnaire"
            value="manual"
            onChange={handleRadioChange}
          />
          <label className="form-check-label" htmlFor="manualQuestionnaire">
            수동 출제
          </label>
        </div>

        <div className="form-check form-check-inline d-flex align-items-center">
          <input
            className="form-check-input mx-2"
            type="radio"
            name="questionnaireType"
            id="automatedQuestionnaire"
            value="automated"
            onChange={handleRadioChange}
          />
          <label className="form-check-label" htmlFor="automatedQuestionnaire">
            자동 출제
          </label>
        </div>
      </div>
      <div className="mt-4 d-flex justify-content-center">
        <button
          type="button"
          className="btn btn-primary"
          onClick={handleSubmit}
        >
          출제
        </button>
      </div>
    </div>
  );
};

export default QuestionForm;
