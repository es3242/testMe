// https://tworab.tistory.com/79
// https://cha4ser.tistory.com/entry/Web-%EB%B6%80%ED%8A%B8%EC%8A%A4%ED%8A%B8%EB%9E%A9%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9B%B9-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EB%A7%8C%EB%93%A4%EA%B8%B0
import React, { useState, useEffect } from "react";
import { Button, Pagination } from "react-bootstrap";
import axios from "axios";
import "./MyPage.scss";
import { useNavigate } from "react-router-dom";

function MyBoard() {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [userFreeboards, setUserFreeboards] = useState([]);

  useEffect(() => {
    getBoardList();
  }, []);

  const getBoardList = async () => {
    const res = await axios
      .get("/mypage2")
      .then(function (response) {
        console.log(response);
        setUser(response.data.user);
        setUserFreeboards(response.data.userFreeboard);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  };

  if (!user) {
    return <div>유저가 없습니다</div>;
  }

  return (
    <div className="container">
      <h2 className="text-center mt-4 mb-3">작성한 글 목록</h2>
      <table className="table table-striped table-hover table-hover text-center">
        <thead>
          <tr>
            <th>ID</th>
            <th>업로드 일시</th>
            <th>파일명</th>
          </tr>
        </thead>
        <tbody>
          {userFreeboards.map((freeboard) => (
            <tr
              className="py-2 board"
              key={freeboard.contentId}
              onClick={() => navigate(`/board-view/`, { state: { freeboard } })}
            >
              <td>{freeboard.contentId}</td>
              <td>{freeboard.title}</td>
              <td>{freeboard.contentAt}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default MyBoard;
