import React from "react";
import sourcevideo from ".././img/course-video.mp4";
import DragDrop from "../components/DragDrop";
import Board from "./Board";

function MainPage() {
  return (
    <div className="align-items-center">
      {/* <div
        className="hero-section bg-dark text-white d-flex text-center align-items-center mb-4"
        style={{ paddingTop: "10rem", paddingBottom: "10rem" }}
      >
        <div className="video-background">
          <video
            playsInline="playsinline"
            autoPlay="autoplay"
            muted="muted"
            loop="loop"
          >
            <source src={sourcevideo} type="video/mp4" />
          </video>
        </div>
        <div className="container text-center">
          <h1 className="display-3 text-white">TestMe</h1>
          <p className="lead text-white">
            TestMe로 문제를 쉽게 생성하고 풀어보세요!
          </p>
        </div>
      </div> */}

      <div className="col-10 mx-auto">
        <DragDrop />
      </div>

      <div className="d-flex justify-content-center mt-4">
        <div className="col-5 mx-3">
          <Board />
        </div>
        <div className="border col-5 mx-3 d-flex align-items-center justify-content-center">
          내가 가입한 스터디룸 목록
        </div>
      </div>

      {/* <div className="container">
        <h1 className="display-9 text-black text-center mb-5">
          우수 학습자 랭킹
        </h1>
      </div>
      <div className="container">
        <table className="table table-striped table-hover">
          <thead>
            <tr>
              <th>순위</th>
              <th>이름</th>
              <th>점수</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td>
              <td>홍길동</td>
              <td>100</td>
            </tr>
          </tbody>
        </table>
      </div> */}
    </div>
  );
}

export default MainPage;
