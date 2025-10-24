import { render, fireEvent } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import AddTaskForm from '../AddTaskForm';

describe('AddTaskForm', () => {
  
  it('captures input and calls onAddTask, then clears fields', async () => {
    const onAddTask = vi.fn();
    const utils = render(<AddTaskForm onAddTask={onAddTask} />);

    const title = utils.getByTestId('title-input');
    const desc = utils.getByTestId('description-input');
    const btn = utils.getByTestId('add-btn');

    await userEvent.type(title, 'Buy milk');
    await userEvent.type(desc, '2 liters of whole milk');

    fireEvent.click(btn);

    expect(onAddTask).toHaveBeenCalledWith('Buy milk', '2 liters of whole milk');
    expect(title).toHaveValue('');
    expect(desc).toHaveValue('');


  });
});
