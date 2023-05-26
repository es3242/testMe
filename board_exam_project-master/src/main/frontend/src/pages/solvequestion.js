import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Col, Row } from "react-bootstrap";

function SolveQuestion() {
  return (
    <>
      <div className="container mt-3 rounded border border-success p-3">
        <Form>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>주관식 컴포넌트 예시</Form.Label>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>답안</Form.Label>
            <Form.Control type="text" placeholder="여기에 답안 입력" />
          </Form.Group>
          <Button variant="primary" type="submit">
            확인
          </Button>
        </Form>
      </div>
      <div className="container mt-3 rounded border border-success p-3">
        <Form>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>객관식 컴포넌트 예시</Form.Label>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>답안</Form.Label>
            <Col sm={10}>
              <Form.Check
                type="radio"
                label="답안1"
                name="formHorizontalRadios"
                id="formHorizontalRadios1"
              />
              <Form.Check
                type="radio"
                label="답안2"
                name="formHorizontalRadios"
                id="formHorizontalRadios2"
              />
              <Form.Check
                type="radio"
                label="답안3"
                name="formHorizontalRadios"
                id="formHorizontalRadios3"
              />
            </Col>
          </Form.Group>
          <Button variant="primary" type="submit">
            확인
          </Button>
        </Form>
      </div>
    </>
  );
}

export default SolveQuestion;
