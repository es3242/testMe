import React from "react";
import logo from "../logo.svg";
import { useNavigate } from "react-router-dom";
import pdfIcon from "../public/img1.png";

const QuestionForm = () => {
  // const logo = [icon]; // Add your desired icons here
  // const randomIcon = icons[Math.floor(Math.random() * icons.length)];
  const navigate = useNavigate();

  const handleSubmit = async () => {
    // 여기에 수동이냐 자동이냐에 따라서 다르게 라우팅
    navigate("/edit");
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
          <h3>CA1_컴퓨터구조 개요p.pdf</h3>
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
