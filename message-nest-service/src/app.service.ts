import { Injectable } from '@nestjs/common';

@Injectable()
export class AppService {
  getHello(payload: any) {
    console.log({ payload });
  }
}
