import {Maintenance} from './Maintenance';

export class CarDTO {
  name: string;
  desc: string;
  type: string;
  make: string;
  model: string;
  year: string;
  id: string;
  odometer: number;
  maintenanceHistory: Maintenance[];
}
