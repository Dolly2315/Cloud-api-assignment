provider "aws" {
  region = "eu-west-2"
}

resource "aws_instance" "micronaut_app" {
  ami           = "ami-00896dbce42c872eb"
  instance_type = "t2.micro"
  subnet_id     = aws_subnet.my_subnet.id
  vpc_security_group_ids = [aws_security_group.allow_http.id]

  tags = {
    Name = "MicronautAppInstance"
  }
}
