/******************************************************************
 * A basic TDEE calculator.
 * 
 * @author Ron Rounsifer
 ******************************************************************/
import React, { Component } from 'react';
import { 
  Layout, 
  Form, 
  Input, 
  Select, 
  Button, 
  Radio, 
  Typography,
  Row,
  Col, 
} from "antd";
import '../styling/TDEE.css';

const { Header, Footer, Content } = Layout;
const { Paragraph } = Typography;
const { Option } = Select;

/******************************************************************
 * A Total Daily Exercise Expenditure calculator form.
 ******************************************************************/
class TDEEForm extends Component {

  state = {
    system: "imperial",
    gender: "male",
    age: "",
    weight: "",
    height: "",
    activity_level: "Select Activity Level",
    bmr: null,
    tdee: null,
    consume: null,
    goal: "Select a Goal",
    goal_weight: "",
    proteins: "",
    fats: "",
    carbs: "",
  };

  /****************************************************************
   * Calculates and displays the users TDEE score.
   ****************************************************************/
  handleSubmit = e => {
    e.preventDefault();
    let temp_bmr = 0.0;
    let { system, gender, age, weight, goal_weight, height, activity_level, goal, proteins, fats, carbs } = this.state;

    // If imperial, convert weight/height to kg/cm
    if (system === "imperial")
    {
      weight = weight / 2.2;
      height = height * 2.54;
    }

    // calculate male/female BMR values
    switch (gender) {
      case "male":
        temp_bmr = Math.round(66 + (13.7 * weight) + (5 * height) - (6.8 * age))
        break;
      case "female":
        temp_bmr = Math.round(655 + (9.6 * weight) + (1.8 * height) - (4.7 * age))
        break;
      default:
        break;
    }

    let temp_tdee = Math.round(temp_bmr * activity_level)
    let temp_consume = 0;

    // Goals: cut, bulk, maintain weight
    switch(goal) {
      case 0:
        temp_consume = temp_tdee - 300;
        break;
      case 1:
        temp_consume = temp_tdee + 300;
        break;
      case 2:
        temp_consume = temp_tdee;
        break;
      default:
        break;
    }
    // Calculate macros
    let remaining_kcal = temp_consume;
    proteins = parseInt(goal_weight) + 10;
    remaining_kcal = remaining_kcal - (proteins * 4);
    fats = Math.round((remaining_kcal/2) / 9);
    carbs = Math.round((remaining_kcal/2) / 4);

    // Convert BMR, TDEE, and consume to strings to output
    temp_tdee = parseInt(Math.round(temp_bmr * activity_level)) + " k/cal";
    temp_bmr = parseInt(temp_bmr) + " k/cal";
    temp_consume = parseInt(temp_consume) + " k/cal";

    // change state of BMR/TDEE values
    this.setState({
      bmr: temp_bmr,
      tdee: temp_tdee,
      consume: temp_consume,
      proteins: proteins,
      fats: fats,
      carbs: carbs,
    })
  }

  /****************************************************************
   * Updates the state whenever the user changes inputs values.
   ****************************************************************/
  handleInputChange = e => {
    const {name, value} = e.target
    this.setState({  
      [name]: value
    })
  }
  
  /****************************************************************
   * Updates the states activity level from the dropdown menu
   ****************************************************************/
  handleActivityLevelChange = e => {
    this.setState({
      activity_level: e
    })
  }

  /****************************************************************
   * Updates the states Goal value from dropdown menu
   ****************************************************************/
  handleGoalChange = e => {
    this.setState({
      goal: e
    })
  }

  /****************************************************************
   * Render method to display the application.
   ****************************************************************/
  render() {
    return (
      <Layout className="App">
        <Header className="App-header">
          <h1>Total Daily Exercise Expenditure</h1>
        </Header>
        <Content className="App-content">
          <Form onSubmit={this.handleSubmit} className="tdee-form">
          <Paragraph className={"Description"}>System of Measurement</Paragraph>
          <Form.Item>
                <Radio.Group onChange={this.handleInputChange} name={"system"} value={this.state.system}>
                  <Radio.Button value={"imperial"}>Imperial</Radio.Button>
                  <Radio.Button value={"metric"}>Metric</Radio.Button>
                </Radio.Group>
            </Form.Item>
            <Paragraph className={"Description"}>Gender</Paragraph>
              <Form.Item>
                <Radio.Group onChange={this.handleInputChange} name={"gender"} value={this.state.gender}>
                  <Radio.Button value={"male"}>Male</Radio.Button>
                  <Radio.Button value={"female"}>Female</Radio.Button>
                </Radio.Group>
              </Form.Item>
              <Form.Item>
                  <Input placeholder="Age" onChange={this.handleInputChange} name={"age"} value={this.state.age}/>
              </Form.Item>
              <Form.Item>
                  <Input placeholder="Weight (lbs or kg)" onChange={this.handleInputChange} name={"weight"} value={this.state.weight}/>
              </Form.Item>
              <Form.Item>
                  <Input placeholder="Goal weight (lbs or kg)" onChange={this.handleInputChange} name={"goal_weight"} value={this.state.goal_weight}/>
              </Form.Item>
              <Form.Item>
                <Input placeholder="Height (inches or cm)" onChange={this.handleInputChange} name={"height"} value={this.state.height}/>
              </Form.Item>
              <Form.Item>
                  <Select defaultValue={1.2} onChange={this.handleActivityLevelChange} name={"activity_level"} value={this.state.activity_level}>
                      <Option value={1.2}>Sedentary (office job)</Option>
                      <Option value={1.375}>Light Exercise (1-2 days/week)</Option>
                      <Option value={1.55}>Moderate Exercise (3-5 days/week)</Option>
                      <Option value={1.725}>Heavy Exercise (6-7 days/week)</Option>
                      <Option value={1.9}>Athlete (2x per day)</Option>
                  </Select>
              </Form.Item>
              <Form.Item>
                  <Select defaultValue={null} onChange={this.handleGoalChange} name={"goal"} value={this.state.goal}>
                      <Option value={0}>Cut Weight</Option>
                      <Option value={1}>Bulk Weight</Option>
                      <Option value={2}>Maintain Weight</Option>
                  </Select>
              </Form.Item>

              <Button type="primary" htmlType="Submit" className="submit-button">
                Calculate
              </Button>
          </Form>
          <div>
            <Row>
              <Col span={12}>
                <Paragraph className="Outputs">BMR: {this.state.bmr}</Paragraph>
                <Paragraph className="Outputs">TDEE: {this.state.tdee}</Paragraph>
                <Paragraph className="Outputs">Goal: {this.state.consume}</Paragraph>
              </Col>
              <Col span={12}>
                <Paragraph className="Outputs">Protein: {this.state.proteins}</Paragraph>
                <Paragraph className="Outputs">Fats: {this.state.fats}</Paragraph>
                <Paragraph className="Outputs">Carbs: {this.state.carbs}</Paragraph>
              </Col>
            </Row>
          </div>
        
        </Content>
        <Footer className="App-footer">
          <Row>
            This calculator is not a nutritional aid as every human body is different. 
          </Row>
          <Row>
            This simply guides users in the correct direction, but all macros can be modified by the user whenever they wish.
          </Row>
          <Row>
            &copy; Ron Rounsifer
          </Row>
        </Footer>
      </Layout>
      );
  }
}

export default TDEEForm;