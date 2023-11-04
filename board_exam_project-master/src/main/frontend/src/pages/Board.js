// https://tworab.tistory.com/79
// https://cha4ser.tistory.com/entry/Web-%EB%B6%80%ED%8A%B8%EC%8A%A4%ED%8A%B8%EB%9E%A9%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9B%B9-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EB%A7%8C%EB%93%A4%EA%B8%B0
import React, { useState, useEffect } from "react";
import { Button, Pagination } from "react-bootstrap";
import axios from "axios";
import "./Board.scss";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import loginState from "../Atoms";

function Board() {
  const navigate = useNavigate();
  const [loggedIn, setLoggedIn] = useRecoilState(loginState);
  const [boardList, setBoardList] = useState([]);

  // const { id } = useParams(); // Accessing the dynamic parameter from the URL

  useEffect(() => {
    getBoardList();
  }, []);

  const getBoardList = async () => {
    const res = await axios
      .get("/list")
      .then(function (response) {
        console.log(response);
        setBoardList(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div className="container">
      <h1 className="text-center mt-3 mb-3">커뮤니티</h1>
      <table className="table table-striped table-hover table-hover text-center">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성일시</th>
          </tr>
        </thead>
        <tbody>
          {boardList.map((board) => (
            <tr
              className="py-2 board"
              key={board.contentId}
              onClick={() => navigate(`/board-view/`, { state: { board } })}
            >
              <td>{board.contentId}</td>
              <td>{board.title}</td>
              <td>{board.contentAt}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <hr />
      {/* <Pagination className="d-flex justify-content-center">
        <Pagination.First />
        <Pagination.Prev />
        <Pagination.Item>{1}</Pagination.Item>
        <Pagination.Ellipsis />

        <Pagination.Item>{10}</Pagination.Item>
        <Pagination.Item>{11}</Pagination.Item>
        <Pagination.Item active>{12}</Pagination.Item>
        <Pagination.Item>{13}</Pagination.Item>
        <Pagination.Item disabled>{14}</Pagination.Item>

        <Pagination.Ellipsis />
        <Pagination.Item>{20}</Pagination.Item>
        <Pagination.Next />
        <Pagination.Last />
      </Pagination> */}
      <div style={{ display: "flex" }}>
        <Button
          variant="success"
          className="float-right mt-3"
          style={{ marginLeft: "auto" }}
          onClick={() => {
            if (loggedIn === "True") {
              navigate("/add");
            } else {
              alert("로그인이 필요합니다.");
            }
          }}
        >
          글쓰기
        </Button>
      </div>
    </div>
  );
}

export default Board;
