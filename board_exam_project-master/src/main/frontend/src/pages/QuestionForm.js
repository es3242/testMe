import React from "react";
import logo from "../logo.svg";

const QuestionForm = () => {
  // const logo = [icon]; // Add your desired icons here
  // const randomIcon = icons[Math.floor(Math.random() * icons.length)];

  return (
    <div className="container">
      <div className="d-flex row mt-4 justify-content-center rounded border border-success font-weight-bold p-3">
        <div className="col-6" style={{ width: "10rem", height: "10rem" }}>
          {/* Random icon */}
          <span className={`icon ${logo}`}>
            <img
              src={logo}
              alt="Another Image"
              style={{ width: "100%", height: "100%" }}
            />
          </span>
        </div>
        <div className="col-6 d-flex flex-column justify-content-center">
          {/* Large title */}
          <h1 className="display-6">PDF 제목 대충 이렇게 뜸</h1>
          {/* Small title */}
          <h4>PDF 다른거 뭐가져올까</h4>
        </div>
      </div>
      <div className="mt-4 d-flex justify-content-center">
        <div className="form-check form-check-inline ">
          <input
            className="form-check-input"
            type="radio"
            name="questionnaireType"
            id="manualQuestionnaire"
            value="manual"
          />
          <label className="form-check-label" htmlFor="manualQuestionnaire">
            Manual Questionnaire
          </label>
        </div>

        <div className="form-check form-check-inline">
          <input
            className="form-check-input"
            type="radio"
            name="questionnaireType"
            id="automatedQuestionnaire"
            value="automated"
          />
          <label className="form-check-label" htmlFor="automatedQuestionnaire">
            Automated Questionnaire
          </label>
        </div>
      </div>
      <div className="mt-4 d-flex justify-content-center">
        <button type="button" className="btn btn-primary">
          Questionnaire
        </button>
      </div>
    </div>
  );
};

export default QuestionForm;
