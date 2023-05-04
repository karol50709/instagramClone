import { type HelloObject } from '../bean/helloObject'
import axios, { type AxiosResponse } from 'axios'

export const getHelloObject = (): Promise<HelloObject> => axios
  .get('http://localhost:3000/helloObject')
  .then((response: AxiosResponse<HelloObject>) => response.data);
