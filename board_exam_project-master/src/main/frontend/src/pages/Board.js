// https://tworab.tistory.com/79
// https://cha4ser.tistory.com/entry/Web-%EB%B6%80%ED%8A%B8%EC%8A%A4%ED%8A%B8%EB%9E%A9%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9B%B9-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EB%A7%8C%EB%93%A4%EA%B8%B0
import React from "react";
import { Button, Pagination } from "react-bootstrap";

function Board() {
  return (
    <div className="container">
      <h1 className="text-center">
        <a href="#">Free Board</a>
      </h1>
      <table
        className="table table-hover table-striped text-center"
        style={{ border: "1px solid" }}
      >
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Title_1</td>
            <td>Cha4ser</td>
            <td>1</td>
          </tr>
          <tr>
            <td>2</td>
            <td>Title_2</td>
            <td>Cha4ser</td>
            <td>10</td>
          </tr>
          <tr>
            <td>3</td>
            <td>Title_3!</td>
            <td>Cha4ser</td>
            <td>12</td>
          </tr>
        </tbody>
      </table>
      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>165</td>
            <td>이제 곧 Bootstrap 4가 나온다면서요?</td>
            <td>길라임</td>
            <td>2016.12.02</td>
            <td>2</td>
          </tr>
          <tr>
            <td>164</td>
            <td>Bootstarp 강좌 입니다</td>
            <td>관리자</td>
            <td>2016.12.01</td>
            <td>10</td>
          </tr>
          <tr>
            <td>163</td>
            <td>2016년을 지나 2017년 새해로</td>
            <td>뚱이</td>
            <td>2016.11.30</td>
            <td>4</td>
          </tr>
        </tbody>
      </table>
      <hr />
      <Pagination className="d-flex justify-content-center">
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
      </Pagination>
      <div style={{ display: "flex" }}>
        <Button
          variant="success"
          className="float-right"
          style={{ marginLeft: "auto" }}
          href="#"
        >
          글쓰기
        </Button>
      </div>
    </div>
  );
}

export default Board;
