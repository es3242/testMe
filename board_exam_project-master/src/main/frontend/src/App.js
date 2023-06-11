// src/main/frontend/src/App.js
// https://velog.io/@u-nij/Spring-Boot-React.js-%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD-%EC%84%B8%ED%8C%85
// https://velog.io/@sians0209/Spring-%EB%A6%AC%EC%95%A1%ED%8A%B8-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%97%B0%EA%B2%B0
// https://7942yongdae.tistory.com/136

import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import axios from "axios";
import { Button, Container, Nav, Navbar } from "react-bootstrap";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  useNavigate,
} from "react-router-dom";
import { Outlet } from "react-router-dom";
import bg from "./img/bg.png";
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

function App() {
  const [hello, setHello] = useState("");
  const [msg, setMsg] = useState([]);
  const [message, setMessage] = useState([]);
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

  /*
  useEffect(() => {
    axios
      .get("/api/hello")
      .then((response) => setHello(response.data))
      .catch((error) => console.log(error));
  }, []);

  useEffect(() => {
    axios
      .get("/list")
      .then((response) => console.log(response.data))
      .catch((error) => console.log(error));
  }, []);


  useEffect(() => {
    fetch("/hello")
      .then((response) => {
        return response.json();
      })
      .then(function (data) {
        setMessage(data);
      });
  }, []);

  useEffect(() => {
    fetch("/api/demo-web")
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setMessage(data);
      });
  }, []);
  */

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
              {/* <Link to="/">홈</Link> */}홈
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
              {/* <Link to="/detail">상세페이지</Link> */}
              문제출제
            </Nav.Link>
            <Nav.Link
              onClick={() => {
                navigate("/board");
              }}
            >
              {/* <Link to="/detail">상세페이지</Link> */}
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
                  {/* <Link to="/detail">상세페이지</Link> */}
                  로그인
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    navigate("/register");
                  }}
                >
                  {/* <Link to="/detail">상세페이지</Link> */}
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
                  {/* <Link to="/detail">상세페이지</Link> */}
                  마이페이지
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    logout();
                  }}
                >
                  {/* <Link to="/detail">상세페이지</Link> */}
                  로그아웃
                </Nav.Link>
              </>
            )}
          </Nav>
        </Container>
      </Navbar>
      <Routes>
        <Route
          path=""
          element={
            <>
              <div className="hero-section bg-dark text-white d-flex text-center align-items-center py-5 mb-4">
                <div className="container text-center">
                  <h1 className="display-3 text-white">TestMe</h1>
                  <p className="lead text-white">
                    TestMe로 문제를 쉽게 생성하고 풀어보세요!
                  </p>
                  {/*<button className="btn btn-primary">아무 일도 일어나지 않는 버튼</button>*/}
                </div>
              </div>
              <div className="container">
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
                    {/* 더 많은 학습자들의 랭킹 정보를 표시하는 로직 */}
                  </tbody>
                </table>
              </div>
              {/*
              
                          백엔드에서 가져온 데이터입니다 : {hello}
              <br />
              백엔드에서 가져온 데이터입니다 : {message}
              <ul>
                {message.map((text, index) => (
                  <li key={`${index}-${text}`}>{text}</li>
                ))}
              </ul>
            
          */}
            </>
          }
        ></Route>
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

        <Route path="/about" element={<About />}>
          <Route path="member" element={<div>멤버임</div>} />
          <Route path="location" element={<div>위치정보임</div>} />
        </Route>
        <Route path="/event" element={<EventPage />}>
          <Route path="one" element={<p>첫 주문시 양배추즙 서비스</p>} />
          <Route path="two" element={<p>생일기념 쿠폰받기</p>} />
        </Route>
        <Route path="*" element={<div>읎어요</div>} />
      </Routes>
    </div>
  );
}

function About() {
  return (
    <div>
      <h4>회사정보임</h4>
      <Outlet>{/* Nested 안에 있는 것들을 보여주는 자리 */}</Outlet>
    </div>
  );
}

function EventPage() {
  return (
    <div>
      <h4>오늘의 이벤트</h4>
      <Outlet>{/* Nested 안에 있는 것들을 보여주는 자리 */}</Outlet>
    </div>
  );
}

export default App;
