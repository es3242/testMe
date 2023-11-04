import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Col, Row } from "react-bootstrap";

// 얘네도 주관식 카드, 객관식 카드 이런 식으로 컴포넌트화 필요할듯
function SolveQuestion() {
  return (
    <>
      <div className="container mt-3 rounded border border-success p-3">
        <Form>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>
              주관식 문제: 논리 게이트 크기를 줄이고, 더 많은 게이트를 더욱
              조밀하게 넣고 " "
            </Form.Label>
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
            <Form.Label>
              객관식 문제: 다음 중 보조기억장치에 대한 설명으로 맞지 않은 것은?
            </Form.Label>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>답안</Form.Label>
            <Col sm={10}>
              <Form.Check
                className="d-flex align-items-center"
                type="radio"
                label={
                  <>
                    <span className="mx-2">
                      주기억장치에 비해서 가격이 비싸다
                    </span>
                    {/* Replace 'mr-2' with the desired margin value */}
                  </>
                }
                name="formHorizontalRadios"
                id="formHorizontalRadios1"
              />
              <Form.Check
                className="d-flex align-items-center"
                type="radio"
                label={
                  <>
                    <span className="mx-2">
                      중앙처리장치와 직접 정보를 교환할 수 없다
                    </span>
                    {/* Replace 'mr-2' with the desired margin value */}
                  </>
                }
                name="formHorizontalRadios"
                id="formHorizontalRadios2"
              />
              <Form.Check
                className="d-flex align-items-center"
                type="radio"
                label={
                  <>
                    <span className="mx-2">
                      데이터를 반영구적으로 저장할 수 있다
                    </span>
                    {/* Replace 'mr-2' with the desired margin value */}
                  </>
                }
                name="formHorizontalRadios"
                id="formHorizontalRadios3"
              />
              <Form.Check
                className="d-flex align-items-center"
                type="radio"
                label={
                  <>
                    <span className="mx-2">
                      하드디스크와 SSD는 보조기억장치이다
                    </span>
                    {/* Replace 'mr-2' with the desired margin value */}
                  </>
                }
                name="formHorizontalRadios"
                id="formHorizontalRadios3"
              />
              <Form.Check
                className="d-flex align-items-center"
                type="radio"
                label={
                  <>
                    <span className="mx-2">
                      주기억장치 대비 저장 용량이 크다
                    </span>
                    {/* Replace 'mr-2' with the desired margin value */}
                  </>
                }
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
