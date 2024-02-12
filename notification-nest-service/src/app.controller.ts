import { Controller } from '@nestjs/common';
import { AppService } from './app.service';
import { EventPattern, Payload } from '@nestjs/microservices';

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @EventPattern('orders-topic')
  getHello(@Payload() payload: any) {
    this.appService.getHello(payload);
  }
}
