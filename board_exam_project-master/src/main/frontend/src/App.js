// src/main/frontend/src/App.js
// https://velog.io/@u-nij/Spring-Boot-React.js-%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD-%EC%84%B8%ED%8C%85
// https://velog.io/@sians0209/Spring-%EB%A6%AC%EC%95%A1%ED%8A%B8-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%97%B0%EA%B2%B0
// https://7942yongdae.tistory.com/136

import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import axios from "axios";
import { Container, Nav, Navbar } from "react-bootstrap";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  useNavigate,
} from "react-router-dom";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Upload from "./pages/Upload";
import Board from "./pages/Board";
import QuestionForm from "./pages/QuestionForm";
import EditQuestion from "./pages/editquestion";
import AllQuestion from "./pages/allquestion";
import SolveQuestion from "./pages/solvequestion";
import MakeComplete from "./pages/makecomplete";
import BoardRegister from "./pages/BoardRegister";
import BoardView from "./pages/BoardView";
import BoardEdit from "./pages/BoardEdit";
import { useRecoilState } from "recoil";
import loginState from "./Atoms";
import MyPage from "./pages/MyPage";
import EditManualQuestion from "./pages/editmanualquestion";
import MainPage from "./pages/MainPage";
import MyPDF from "./pages/MyPDF";
import MyBoard from "./pages/MyBoard";

function App() {
  const [loggedIn, setLoggedIn] = useRecoilState(loginState);
  let navigate = useNavigate();

  const logout = async () => {
    const res = await axios
      .get("/logout")
      .then(function (response) {
        console.log(response.data);
        sessionStorage.removeItem("loggedIn");
        setLoggedIn("False");
        alert("성공적으로 로그아웃했습니다!");
        // 홈으로 넘겨주기
        navigate("/");
      })
      .catch((error) => {
        console.error(
          "Error during getting session info:",
          error.response.data
        );
      });
  };

  return (
    <div>
      <Navbar bg="light" variant="light">
        <Container>
          <Navbar.Brand href="/">TestMe</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link
              onClick={() => {
                navigate("/");
              }}
            >
              홈
            </Nav.Link>
            <Nav.Link
              onClick={() => {
                if (loggedIn === "True") {
                  navigate("/upload");
                } else {
                  alert("로그인이 필요합니다!");
                }
              }}
            >
              문제출제
            </Nav.Link>
            <Nav.Link
              onClick={() => {
                navigate("/board");
              }}
            >
              커뮤니티
            </Nav.Link>
          </Nav>
          <Nav className="ms-auto">
            {loggedIn === "False" ? (
              <>
                <Nav.Link
                  onClick={() => {
                    navigate("/login");
                  }}
                >
                  로그인
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    navigate("/register");
                  }}
                >
                  회원가입
                </Nav.Link>
              </>
            ) : (
              <>
                <Nav.Link
                  onClick={() => {
                    navigate("/mypage");
                  }}
                >
                  마이페이지
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    logout();
                  }}
                >
                  로그아웃
                </Nav.Link>
              </>
            )}
          </Nav>
        </Container>
      </Navbar>
      <Routes>
        <Route path="" element={<MainPage />}></Route>
        <Route path="/make" element={<QuestionForm />}></Route>
        <Route path="/makecomplete" element={<MakeComplete />}></Route>
        <Route path="/edit" element={<EditQuestion />}></Route>
        <Route path="/manualedit" element={<EditManualQuestion />}></Route>
        <Route path="/allquestion" element={<AllQuestion />}></Route>
        <Route path="/solvequestion" element={<SolveQuestion />}></Route>
        <Route path="/register" element={<Register />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/upload" element={<Upload />}></Route>
        <Route path="/board" element={<Board />}></Route>
        <Route path="/add" element={<BoardRegister />}></Route>
        <Route path="/board-view/" element={<BoardView />}></Route>
        <Route path="/board-edit/" element={<BoardEdit />}></Route>
        <Route path="/mypage" element={<MyPage />}></Route>
        <Route path="/myPDF" element={<MyPDF />}></Route>
        <Route path="/myBoard" element={<MyBoard />}></Route>
        <Route path="*" element={<div>읎어요</div>} />
      </Routes>
    </div>
  );
}

export default App;
