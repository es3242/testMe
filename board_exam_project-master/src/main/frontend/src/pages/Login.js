import { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import axios from "axios";
import { useRecoilState } from "recoil";
import { useNavigate } from "react-router-dom";
import loginState from "../Atoms";

function Login() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [loggedIn, setLoggedIn] = useRecoilState(loginState);
  const navigate = useNavigate();

  const login = async (id, password) => {
    const res = await axios
      .post("/login", null, {
        params: {
          id,
          password,
        },
      })
      .then(function (response) {
        console.log(response);
        sessionStorage.setItem("loggedIn", id);
        setLoggedIn("True");
        // 홈으로 넘기기
        navigate("/");
      })
      .catch((error) => {
        console.error(error.response.data);
      });
  };

  const getSessionInfo = async () => {
    const res = await axios
      .get("/session-info")
      .then(function (response) {
        console.log("지금 로그인한 사용자: ", response.data);
      })
      .catch((error) => {
        console.error(
          "Error during getting session info:",
          error.response.data
        );
      });
  };

  const logout = async () => {
    const res = await axios
      .get("/logout")
      .then(function (response) {
        console.log(response.data);
        sessionStorage.removeItem("loggedIn");
        setLoggedIn("False");
        // 어딘가로 넘겨주기
      })
      .catch((error) => {
        console.error(
          "Error during getting session info:",
          error.response.data
        );
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(id);
    console.log(password);
    if (loggedIn === "True") {
      alert("이미 로그인되어 있습니다!");
    } else {
      login(id, password);
    }
  };

  return (
    <div>
      <div className="container mt-3">
        <h1 className="d-flex justify-content-center mt-7 mb-3">로그인</h1>
        <Form method="post" action="/login" onSubmit={handleSubmit}>
          <Form.Group className="mb-3" controlId="formBasicID">
            <Form.Label>ID</Form.Label>
            <Form.Control
              type="text"
              name="id"
              placeholder="ID를 입력하세요"
              required
              onChange={(e) => setId(e.target.value)}
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>비밀번호</Form.Label>
            <Form.Control
              type="password"
              name="password"
              placeholder="비밀번호를 입력하세요"
              className=""
              required
              onChange={(e) => setPassword(e.target.value)}
            />
          </Form.Group>

          <Button variant="primary" type="submit">
            로그인
          </Button>
        </Form>

        {/* <button
          onClick={() => {
            getSessionInfo();
          }}
        >
          세션 정보 받아오기
        </button> */}

        {/* <button
          onClick={() => {
            logout();
          }}
        >
          로그아웃
        </button> */}
      </div>
    </div>
  );
}

export default Login;
