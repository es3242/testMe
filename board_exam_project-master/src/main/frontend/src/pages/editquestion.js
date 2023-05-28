import React, { useState, useEffect } from "react";
import {
  Container,
  Row,
  Col,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
} from "react-bootstrap";
import EditSubjective from "../components/editSubjective";
import EditChoice from "../components/editChoice";

function EditQuestion() {
  return (
    <div>
      {/* <EditSubjective /> */}
      <EditChoice />
    </div>
  );
}

export default EditQuestion;
