import React, { useEffect, useState } from "react";
import { useNavigate, useParams, useLocation } from "react-router-dom";
import axios from "axios";
import { Button, Card, Modal, Col } from "react-bootstrap";
import CommentList from "../components/CommentList";

// match 안에 URL에 붙어오는 값 담김
const BoardView = ({}) => {
  const navigate = useNavigate();
  const location = useLocation();
  let board = null;
  if (location.state.board !== undefined) {
    board = location.state.board;
  } else if (location.state.freeboard !== undefined) {
    board = location.state.freeboard;
  }

  // Modal
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  useEffect(() => {
    console.log(board);
  }, []);

  const handleDelete = async (event) => {
    event.preventDefault();
    const res = await axios.get(`/delete/${board?.contentId}`);
    setShow(false);
    navigate(-1);
  };

  return (
    <div className="container">
      <Card className="p-3 my-3">
        <Card.Title
          className="pb-2"
          style={{ borderBottom: "1px solid #dddddd" }}
        >
          {board?.title}
        </Card.Title>
        <Card.Text>{board?.content}</Card.Text>
      </Card>
      <Col className="d-flex justify-content-end">
        <Button
          variant="info"
          onClick={() => navigate(`/board-edit/`, { state: { board } })}
          className="mr-3"
        >
          수정
        </Button>
        <Button variant="danger" onClick={() => handleShow()} className="mx-3">
          삭제
        </Button>
        <Button variant="primary" onClick={() => navigate(-1)} className="ml-3">
          돌아가기
        </Button>
      </Col>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>삭제</Modal.Title>
        </Modal.Header>
        <Modal.Body>삭제하시겠습니까?</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cancel
          </Button>
          <Button variant="primary" onClick={handleDelete}>
            OK
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default BoardView;
