import React from "react";
import logo from "../logo.svg";
import { useNavigate } from "react-router-dom";
import pdfIcon from "../public/img1.png";

const MakeComplete = () => {
  // const logo = [icon]; // Add your desired icons here
  // const randomIcon = icons[Math.floor(Math.random() * icons.length)];
  const navigate = useNavigate();

  const handleSolve = async () => {
    // 여기에 수동이냐 자동이냐에 따라서 다르게 라우팅
    navigate("/solvequestion");
  };

  const handleHome = async () => {
    navigate("/");
  };

  return (
    <div className="container">
      <h1 className="display-9 text-black text-center mt-3">
        출제가 완료되었습니다!
      </h1>
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
        <button
          type="button"
          className="btn btn-primary mx-3"
          onClick={handleSolve}
        >
          풀어보기
        </button>

        <button
          type="button"
          className="btn btn-primary mx-3"
          onClick={handleHome}
        >
          처음으로
        </button>
      </div>
    </div>
  );
};

export default MakeComplete;
