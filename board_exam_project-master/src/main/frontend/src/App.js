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
import RankingTable from "./pages/RankingTable";
import Upload from "./pages/Upload";

function App() {
  const [hello, setHello] = useState("");
  const [msg, setMsg] = useState([]);
  const [message, setMessage] = useState([]);
  let navigate = useNavigate();

  useEffect(() => {
    axios
      .get("/api/hello")
      .then((response) => setHello(response.data))
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
                navigate("/board");
              }}
            >
              {/* <Link to="/detail">상세페이지</Link> */}
              자유 게시판 list
            </Nav.Link>
            <Nav.Link
              onClick={() => {
                navigate("/board");
              }}
            >
              {/* <Link to="/detail">상세페이지</Link> */}
              자유 게시판 add
            </Nav.Link>
            <Nav.Link
              onClick={() => {
                navigate("/upload");
              }}
            >
              {/* <Link to="/detail">상세페이지</Link> */}
              미리보기 테스트
            </Nav.Link>
            <Nav.Link
              onClick={() => {
                navigate("/ranking");
              }}
            >
              {/* <Link to="/detail">상세페이지</Link> */}
              랭킹
            </Nav.Link>
            <Nav.Link
              onClick={() => {
                navigate("/register");
              }}
            >
              {/* <Link to="/detail">상세페이지</Link> */}
              회원가입
            </Nav.Link>
            <Nav.Link
              onClick={() => {
                navigate("/login");
              }}
            >
              {/* <Link to="/detail">상세페이지</Link> */}
              로그인
            </Nav.Link>
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
                    강의자료로 문제 출제하는거임
                  </p>
                  {/*<button className="btn btn-primary">아무 일도 일어나지 않는 버튼</button>*/}
                </div>
              </div>
              <div className="container">
                <div className="row">
                  {/* First Card */}
                  <div className="col-sm-4 mb-4">
                    <div className="card h-100">
                      <img
                        src="https://source.unsplash.com/1600x900/?refrigerator"
                        className="card-img-top"
                        alt="Refrigerator Image"
                      ></img>
                      <div className="card-body">
                        <h5 className="card-title">냉장고</h5>
                        <h6 className="card-subtitle mb-2 text-muted">
                          89.99$
                        </h6>
                        <p className="card-text">
                          한 번 밖에 안쓴 냉장고 팝니다. 부모님이 독립 할 때
                          주신 거에요!
                        </p>
                        <a
                          href="https://codingapple.com/"
                          className="card-link stretched-link"
                          target="_blank"
                        ></a>
                      </div>
                    </div>
                  </div>

                  {/* Second Card */}
                  <div className="col-sm-4 mb-4">
                    <div className="card h-100">
                      <img
                        src="https://source.unsplash.com/1600x900/?washing-machine"
                        className="card-img-top"
                        alt="Washing Machine Image"
                      ></img>
                      <div className="card-body">
                        <h5 className="card-title">세탁기</h5>
                        <h6 className="card-subtitle mb-2 text-muted">
                          109.99$
                        </h6>
                        <p className="card-text">
                          한 번 밖에 안쓴 세탁기 팝니다. 지방 발령 때문에 샀는데
                          취소됐어요 ㅠㅠ
                        </p>
                        <a
                          href="https://codingapple.com/"
                          className="card-link stretched-link"
                          target="_blank"
                        ></a>
                      </div>
                    </div>
                  </div>

                  {/* Third Card */}
                  <div className="col-sm-4 mb-4">
                    <div className="card h-100">
                      <img
                        src="https://source.unsplash.com/1600x900/?laptop"
                        className="card-img-top"
                        alt="Laptop Image"
                      ></img>
                      <div className="card-body">
                        <h5 className="card-title">노트북</h5>
                        <h6 className="card-subtitle mb-2 text-muted">
                          199.99$
                        </h6>
                        <p className="card-text">
                          노트북 팝니다. 직장 다니면서 쓰던 건데, 쓰다가 마음에
                          안 들어서 이직 이후로 두고두고 안 썼어요.
                        </p>
                        <a
                          href="https://codingapple.com/"
                          className="card-link stretched-link"
                          target="_blank"
                        ></a>
                      </div>
                    </div>
                  </div>

                  {/* Fourth Card */}
                  <div className="col-sm-4 mb-4">
                    <div className="card h-100">
                      <img
                        src="https://source.unsplash.com/1600x900/?nintendo-switch"
                        className="card-img-top"
                        alt="루타이원"
                      ></img>
                      <div className="card-body">
                        <h5 className="card-title">닌텐도 스위치</h5>
                        <h6 className="card-subtitle mb-2 text-muted">
                          129.99$
                        </h6>
                        <p className="card-text">
                          닌텐도 스위치 팝니다 엄마가 공부해야된다고 올리라고
                          해서 어쩔 수 없이 올려요 ㅠ
                        </p>
                        <a
                          href="https://codingapple.com/"
                          className="card-link stretched-link"
                          target="_blank"
                        ></a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="list-container">
                <ul className="list">
                  <li className="list-item">List Item 1</li>
                  <li className="list-item">List Item 2</li>
                  <li className="list-item">List Item 3</li>
                  <li className="list-item">List Item 4</li>
                </ul>
              </div>
              백엔드에서 가져온 데이터입니다 : {hello}
              <br />
              백엔드에서 가져온 데이터입니다 : {message}
              <ul>
                {message.map((text, index) => (
                  <li key={`${index}-${text}`}>{text}</li>
                ))}
              </ul>
            </>
          }
        ></Route>
        <Route path="/register" element={<Register />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/ranking" element={<RankingTable />}></Route>
        <Route path="/upload" element={<Upload />}></Route>

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
