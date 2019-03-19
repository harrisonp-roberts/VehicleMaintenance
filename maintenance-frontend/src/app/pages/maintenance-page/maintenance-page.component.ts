import {Component, OnInit} from '@angular/core';
import {CarDTO} from '../../models/Car';
import {CarServiceService} from '../../services/car-service.service';
import {FormBuilder, Validators} from '@angular/forms';
import {Maintenance} from '../../models/Maintenance';

@Component({
  selector: 'app-maintenance-page',
  templateUrl: './maintenance-page.component.html',
  styleUrls: ['./maintenance-page.component.css']
})
export class MaintenancePageComponent implements OnInit {
  allCars: CarDTO[];
  selectedCar: CarDTO;
  showAddCar: boolean;
  showMaintenance: boolean;

  addCarForm = this.formBuilder.group({
    make: ['', Validators.required],
    model: ['', Validators.required],
    name: ['', Validators.required],
    description: ['', Validators.required],
    year: ['', Validators.required],
    odometer: ['', Validators.required],
    type: ['', Validators.required]
  });

  addMaintenanceForm = this.formBuilder.group( {
    maintenanceType: ['', Validators.required],
    maintenanceDate: ['', Validators.required]
  });

  constructor(private carService: CarServiceService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.carService.getAllCars().subscribe(data => {
      this.allCars = data;
    });
  }

  onSave() {
    const toSave = new CarDTO();

    toSave.name = this.addCarForm.get('name').value;
    toSave.desc = this.addCarForm.get('description').value;
    toSave.make = this.addCarForm.get('make').value;
    toSave.model = this.addCarForm.get('model').value;
    toSave.odometer = this.addCarForm.get('odometer').value;
    toSave.type = this.addCarForm.get('type').value;
    toSave.year = this.addCarForm.get('year').value;
    toSave.id = '';
    toSave.maintenanceHistory = null;

    if(this.addCarForm.valid) {
      this.carService.persistCar(toSave).subscribe(data => {
        console.log(data);
        this.showAddCar = false;
        this.ngOnInit();

      });
    }

  }

  viewMaintenance(car: CarDTO) {
    this.showMaintenance = true;
    this.showAddCar = false;
    this.selectedCar = car;
  }

  addCar() {
    this.showAddCar = true;
    this.showMaintenance = false;
  }

  addMaintenance(selectedCar: CarDTO) {
    const newMaintenance = new Maintenance();
    newMaintenance.type = this.addMaintenanceForm.get('maintenanceType').value;
    newMaintenance.date = this.addMaintenanceForm.get('maintenanceDate').value;

    console.log(newMaintenance.date);

    if (this.addMaintenanceForm.valid) {
      selectedCar.maintenanceHistory.push(newMaintenance);
      this.carService.persistCar(selectedCar).subscribe(data => {
        console.log(data);
        this.ngOnInit();
      });
    }
  }
}
