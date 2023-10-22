// https://tworab.tistory.com/79
// https://cha4ser.tistory.com/entry/Web-%EB%B6%80%ED%8A%B8%EC%8A%A4%ED%8A%B8%EB%9E%A9%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9B%B9-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EB%A7%8C%EB%93%A4%EA%B8%B0
import React, { useState, useEffect } from "react";
import { Button, Pagination } from "react-bootstrap";
import axios from "axios";
import "./MyPage.scss";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import loginState from "../Atoms";
import logo from "../logo.svg";
import pdfIcon from "../public/img1.png";

function MyPDF() {
  const navigate = useNavigate();
  // const [loggedIn, setLoggedIn] = useRecoilState(loginState);
  const [pdfList, setPdfList] = useState([]);
  const [user, setUser] = useState(null);
  const [userFreeboards, setUserFreeboards] = useState([]);

  // const { id } = useParams(); // Accessing the dynamic parameter from the URL

  // 유저 정보만 불러오게 바꿔야함
  useEffect(() => {
    getBoardList();
    getPdfList();
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

  const getPdfList = async () => {
    const res = await axios
      .get("/pdflist")
      .then(function (response) {
        console.log(response);
        setPdfList(response.data);
        console.log(pdfList);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  };

  const getPdf = async (filePath) => {
    const res = await axios
      .post("/pdf/view", null, {
        params: {
          filePath,
        },
      })
      .then(function (response) {
        console.log(response);
      })
      .catch((error) => {
        console.error(error.response.data);
      });
  };

  if (!user) {
    return <div>유저가 없습니다</div>;
  }

  return (
    <div className="container">
      <h2 className="text-center mt-4 mb-3">업로드한 PDF 목록</h2>
      <table className="table table-striped table-hover table-hover text-center">
        <thead>
          <tr>
            <th>ID</th>
            <th>제목</th>
            <th>작성일시</th>
          </tr>
        </thead>
        <tbody>
          {pdfList.map((pdfList) => (
            <tr
              className="py-2 board"
              key={pdfList.pdfId}
              onClick={() => getPdf(pdfList.filePath)}
            >
              <td>{pdfList.pdfId}</td>
              <td>{pdfList.filePath}</td>
              <td>{pdfList.createdAt}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default MyPDF;
